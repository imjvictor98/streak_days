## 1. Setup & Dependencies

- [x] 1.1 Adicionar dependências do Jetpack Glance e WorkManager no `app/build.gradle.kts`.

## 2. Domain & Data Layer

- [x] 2.1 Criar função utilitária no domínio para calcular quais dias da semana atual estão completados dado um `Goal` (usado para renderizar o calendário semanal).
- [x] 2.2 Adicionar testes unitários para a função utilitária de cálculo de semana (`GoalWeeklyProgressTest.kt`).
- [x] 2.3 Expor no `GoalsRepository` ou em um UseCase um método que retorna o "objetivo mais relevante" para o widget ler.

## 3. UI Layer (Jetpack Glance Widget)

- [x] 3.1 Criar a classe base do Widget estendendo `GlanceAppWidget` (`StreakDaysWidget.kt`).
- [x] 3.2 Implementar a UI do Widget com composables do Glance, criando componentes separados para o cabeçalho, calendário da semana e barra de progresso inferior.
- [x] 3.3 Criar a classe `StreakDaysWidgetReceiver` estendendo `GlanceAppWidgetReceiver`.
- [x] 3.4 Registrar o `StreakDaysWidgetReceiver` no `AndroidManifest.xml` e criar o arquivo `xml/streak_days_widget_info.xml`.

## 4. Updates & WorkManager

- [x] 4.1 Modificar o `GoalsViewModel` ou UseCase de "Logar Progresso" para chamar a atualização do Widget (`StreakDaysWidget().updateAll(context)`).
- [x] 4.2 Criar um `Worker` do WorkManager (`WidgetDailyUpdateWorker.kt`) para forçar a atualização do widget na virada do dia.
- [x] 4.3 Agendar o `WidgetDailyUpdateWorker` no inicializador do App ou no `MainActivity` para rodar diariamente.
