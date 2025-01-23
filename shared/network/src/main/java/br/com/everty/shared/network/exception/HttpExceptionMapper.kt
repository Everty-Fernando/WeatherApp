package br.com.everty.shared.network.exception

import retrofit2.Response

object HttpExceptionMapper {

    /**
     * Maps an HTTP response to a custom exception based on its status code.
     * @param response The HTTP response to map.
     * @return Exception The mapped exception based on the HTTP status code.
     */
    fun mapHttpException(response: Response<*>): Exception {
        return when (response.code()) {
            400 -> IllegalArgumentException("Solicitação inválida. Verifique os parâmetros da solicitação.")
            401 -> SecurityException("Token de API não autorizado ou ausente.")
            404 -> NoSuchElementException("Dados não encontrados para os parâmetros fornecidos.")
            429 -> IllegalStateException("Muitas solicitações realizadas. Aguarde antes de tentar novamente.")
            in 500..599 -> IllegalStateException("Erro inesperado no servidor. Tente novamente mais tarde.")
            else -> Exception("Erro desconhecido. Código: ${response.code()}")
        }
    }
}