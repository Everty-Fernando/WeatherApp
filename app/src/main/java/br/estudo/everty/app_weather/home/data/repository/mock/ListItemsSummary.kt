package br.estudo.everty.app_weather.home.data.repository.mock

import android.content.Context
import br.estudo.everty.app_weather.R
import br.estudo.everty.app_weather.home.data.model.ItemSummary

fun getItemsSummary(context: Context): List<ItemSummary> {
    return listOf(
        ItemSummary(context.getString(R.string.item_summary_min_max), icon = R.drawable.ic_thermometer),
        ItemSummary(title = context.getString(R.string.item_summary_sun_set), icon = R.drawable.ic_sun),
        ItemSummary(title = context.getString(R.string.item_summary_rain), icon = R.drawable.ic_cloud),
        ItemSummary(title = context.getString(R.string.item_summary_sun_rise), icon = R.drawable.ic_sun),
        ItemSummary(title = context.getString(R.string.item_summary_wind),  icon = R.drawable.ic_wind),
        ItemSummary(title = context.getString(R.string.item_summary_drop),  icon = R.drawable.ic_drop),
    )
}