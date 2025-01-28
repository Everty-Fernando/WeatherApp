package br.com.everty.home.home_ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import br.com.everty.home.home_ui.ui.screen.HomeScreen
import br.com.everty.home.home_ui.ui.viewmodel.HomeViewModel
import br.com.everty.home.home_ui.events.HomeEvents
import br.com.everty.home.home_ui.model.ErrorState
import br.com.everty.home.home_ui.state.HomeStateUI
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme
import br.com.everty.shared.utils.extensions.openLocationSettings
import br.com.everty.shared.utils.extensions.redirectToAppSettings
import br.com.everty.shared.utils.extensions.runAfter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModel {
        parametersOf(HomeStateUI())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppWeatherTheme {
                    HomeScreen(
                        homeStateUI = viewModel.homeStateUI,
                        homeEvents = homeEvents
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkLocationEnabled()
        handleInitialSetup()
    }

    private val homeEvents = object: HomeEvents {
        override fun onClickError() {
            when (viewModel.homeStateUI.errorState) {
                ErrorState.LocationDisabled -> openLocationSettings(
                    requireActivity()
                )
                ErrorState.LocationPermissionDenied -> redirectToAppSettings(
                    requireActivity()
                )
                else -> {
                    viewModel.cleanErrorState()
                    viewModel.setLoading(true)

                    runAfter {
                        if (viewModel.latitude != 0.0 && viewModel.longitude != 0.0) {
                            viewModel.checkNetworkAndFetchData()
                        } else requestPermissionLocale()
                    }
                }
            }
        }

        override fun onFilterSelected(isDaily: Boolean) {
            viewModel.onFilterSelected(isDaily)
        }
    }

    private fun handleInitialSetup() {
        if (viewModel.homeStateUI.errorState == null) {
            viewModel.setLoading(true)
            runAfter {  requestPermissionLocale() }
        }
    }

    private fun requestPermissionLocale() {
        viewModel.requestPermissionLocale(
            activity = requireActivity(),
            onPermissionGranted = {
                viewModel.getDeviceLocation()
            }
        )
    }
}