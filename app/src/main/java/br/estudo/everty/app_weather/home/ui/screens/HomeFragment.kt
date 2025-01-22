package br.estudo.everty.app_weather.home.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import br.estudo.everty.app_weather.home.ui.screens.events.HomeEvents
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState
import br.estudo.everty.app_weather.home.ui.screens.viewmodel.HomeViewModel
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme
import br.com.everty.shared.utils.extensions.openLocationSettings
import br.com.everty.shared.utils.extensions.redirectToAppSettings
import br.com.everty.shared.utils.extensions.runAfter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

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
                ErrorState.LOCATION_DISABLED -> openLocationSettings(
                    requireActivity()
                )
                ErrorState.LOCATION_PERMISSION_DENIED -> redirectToAppSettings(
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