package br.com.everty.home.home_domain.mock

import android.content.Context
import br.com.everty.shared.presentation.R
import br.com.everty.home.home_domain.model.ItemSummaryUI

fun getItemsSummary(context: Context): List<ItemSummaryUI> {
    return listOf(
        ItemSummaryUI(context.getString(R.string.item_summary_min_max), icon = R.drawable.ic_thermometer),
        ItemSummaryUI(title = context.getString(R.string.item_summary_sun_set), icon = R.drawable.ic_sun),
        ItemSummaryUI(title = context.getString(R.string.item_summary_rain), icon = R.drawable.ic_cloud),
        ItemSummaryUI(title = context.getString(R.string.item_summary_sun_rise), icon = R.drawable.ic_sun),
        ItemSummaryUI(title = context.getString(R.string.item_summary_wind),  icon = R.drawable.ic_wind),
        ItemSummaryUI(title = context.getString(R.string.item_summary_drop),  icon = R.drawable.ic_drop),
    )
}