package br.estudo.everty.app_weather.home.domain.mappers

import android.content.Context
import br.estudo.everty.app_weather.R
import br.estudo.everty.app_weather.home.domain.model.ItemSummaryUI
import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import br.estudo.everty.app_weather.home.data.repository.mock.getItemsSummary
import br.estudo.everty.app_weather.utils.extensions.FORMAT_HOURS_MINUTES
import br.estudo.everty.app_weather.utils.extensions.convertTimestampToLocalDate
import br.estudo.everty.app_weather.utils.extensions.convertTimestampToString
import br.estudo.everty.app_weather.utils.extensions.toDegreeCelsius
import br.estudo.everty.app_weather.utils.mappers.Mapper

class SummaryUIMapper(
    private val context: Context
): Mapper<MeteorologicalDataResponse, List<ItemSummaryUI>> {

    override fun toObject(fromObject: MeteorologicalDataResponse): List<ItemSummaryUI> {
        val listItems = getItemsSummary(context)

        fromObject.current.apply {
            listItems.forEach {  item ->
                when(item.title) {
                    context.getString(R.string.item_summary_min_max) -> item.value = getMinMaxTemperature(fromObject)

                    context.getString(R.string.item_summary_sun_set) -> item.value =  sunset.convertTimestampToString(FORMAT_HOURS_MINUTES)

                    context.getString(R.string.item_summary_sun_rise) -> item.value =  sunrise.convertTimestampToString(FORMAT_HOURS_MINUTES)

                    context.getString(R.string.item_summary_rain) -> item.value =  "${clouds.toInt()}%"

                    context.getString(R.string.item_summary_wind) -> item.value =  "$windSpeed km/h"

                    context.getString(R.string.item_summary_drop) -> item.value =  "${humidity.toInt()}%"
                }
            }
        }


        return listItems
    }

    private fun getMinMaxTemperature(meteorologicalDataResponse: MeteorologicalDataResponse): String {
        meteorologicalDataResponse.apply {
            val dayCurrentDate = current.date.convertTimestampToLocalDate().dayOfMonth
            daily.forEach { daily ->
                val dateDaily = daily.date.convertTimestampToLocalDate().dayOfMonth
                if (dayCurrentDate == dateDaily) {
                    return "${daily.temperature.min.toDegreeCelsius()}/${daily.temperature.max.toDegreeCelsius()}"
                }
            }
        }
        return ""
    }
}
