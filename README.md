# WeatherApp

WeatherApp é um aplicativo de previsão do tempo desenvolvido em Kotlin, seguindo a arquitetura MVVM (Model-View-ViewModel). O aplicativo faz uso do Retrofit para consumir a API REST fornecida pela OpenWeatherMap para obter dados meteorológicos e utiliza injeção de dependência com Koin para gerenciar os componentes. As telas são desenvolvidas em Jetpack Compose, oferecendo uma experiência moderna e responsiva para os usuários.

## Recursos

- Obtenha informações detalhadas sobre as condições climáticas atuais e futuras
- Previsões por hora e por semana para planejar seu dia
- [Pendente implementação] Alertas meteorológicos em tempo real para se manter informado sobre mudanças repentinas no clima
- [Pendente implementação] Pesquise o clima em locais específicos em todo o mundo
- Interface intuitiva e responsiva desenvolvida com Jetpack Compose

## API OpenWeatherMap

O WeatherApp utiliza a API OpenWeatherMap para obter dados meteorológicos. Para mais informações sobre a API e como obter uma chave de API, visite: [OpenWeatherMap API](https://openweathermap.org/api).

## Design

O design do WeatherApp foi inspirado em modelos gratuitos disponíveis no Figma, sem quaisquer intenções de fins lucrativos.

## Como usar

Para executar o aplicativo, siga estas etapas:

1. Clone este repositório: `git clone https://github.com/seu_usuario/clima-agora.git`
2. Abra o projeto no Android Studio ou em sua IDE preferida.
3. Obtenha uma chave de API no OpenWeatherMap seguindo as instruções em [OpenWeatherMap API](https://openweathermap.org/api).
4. No arquivo `build.gradle.kts`, adicione sua chave de API à variável `API_KEY`.
   ```kotlin
   // build.gradle.kts

   android {
       // Outras configurações do Android aqui
       
       // Adicione sua chave de API aqui
       val API_KEY = "sua_chave_de_api_aqui"
       
       defaultConfig {
         val API_KEY = "sua_chave_de_api_aqui"
         buildConfigField("String", "API_KEY", "\"${API_KEY}\"")
      }
   }


## Contribuições

Contribuições são bem-vindas! Se você encontrar algum problema, tiver uma sugestão ou desejar contribuir com código, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
