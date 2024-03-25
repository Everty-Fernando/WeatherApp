package br.estudo.everty.app_weather.home.ui.screens.model

import br.estudo.everty.app_weather.R

enum class ErrorState(
    val title: String,
    val description: String,
    val textButton: String,
    val icon: Int
) {
    LOCATION_DISABLED("Localização desativada", "Ops! Parece que a sua localização está desativada. Por favor ative o GPS do seu dispositivo e tente novamente.", "Ativar localização", R.drawable.no_gps),
    NETWORK_DISABLED("Sem conexão com a internet", "Por favor verifique sua internet ou tente novamente", "Tentar novamente", R.drawable.error_network),
    LOCATION_PERMISSION_DENIED("Permissão de Localização Negada", "Para acessar recursos de localização, é necessário conceder permissão. Por favor, vá para as configurações do aplicativo e conceda permissão de localização para continuar.", "Configurar permissão",R.drawable.no_gps),
    ERROR_GET_LOCATION("Erro ao Obter Localização", "Lamentamos, ocorreu uma falha ao tentar obter sua localização atual. Por favor, verifique sua conexão com a internet e certifique-se de ter ativado os serviços de localização em seu dispositivo.", "Tentar novamente",R.drawable.generic_error),
    ERROR_SERVER("Erro no servidor", "Lamentamos, ocorreu uma falha ao recuperar as informações meteorológicas. Por favor, verifique sua conexão com a internet e tente novamente mais tarde.", "Tentar novamente",R.drawable.generic_error)
}
