package br.com.everty.home.home_ui.model


import br.com.everty.shared.presentation.R

sealed class ErrorState(
    val title: String,
    val description: String,
    val textButton: String,
    val icon: Int
) {
    object LocationDisabled : ErrorState(
        "Localização desativada",
        "Ops! Parece que a sua localização está desativada. Por favor ative o GPS do seu dispositivo e tente novamente.",
        "Ativar localização",
        R.drawable.no_gps
    )

    object LocationPermissionDenied : ErrorState(
        "Permissão de Localização Negada",
        "Para acessar recursos de localização, é necessário conceder permissão. Por favor, vá para as configurações do aplicativo e conceda permissão de localização para continuar.",
        "Configurar permissão",
        R.drawable.no_gps
    )
    object LocationGetError : ErrorState(
        "Erro ao Obter Localização",
        "Lamentamos, ocorreu uma falha ao tentar obter sua localização atual. Por favor, verifique sua conexão com a internet e certifique-se de ter ativado os serviços de localização em seu dispositivo.",
        "Tentar novamente",
        R.drawable.generic_error
    )

    object NetworkDisabled : ErrorState(
        "Sem conexão com a internet",
        "Por favor verifique sua internet ou tente novamente",
        "Tentar novamente",
        R.drawable.error_network
    )

    data class ServerError(val dynamicDescription: String) : ErrorState(
        "Erro no servidor",
        dynamicDescription,
        "Tentar novamente",
        R.drawable.generic_error
    )
}