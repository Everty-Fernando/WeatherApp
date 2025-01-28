package br.com.everty.home.home_domain.model


data class WeatherDataUI(
    val date: String,
    val listSummary: List<ItemSummaryUI>,
    val temperature: String,
    val description: String,
    val icon: Int
)