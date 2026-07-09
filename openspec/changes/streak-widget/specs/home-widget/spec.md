## ADDED Requirements

### Requirement: Exibição Básica do Widget
O widget SHALL exibir as informações principais do objetivo ativo mais relevante, incluindo o nome, progresso numérico e barra de progresso horizontal.

#### Scenario: Widget é exibido com sucesso
- **WHEN** o usuário adiciona o widget à tela inicial
- **THEN** o widget deve carregar o objetivo principal
- **THEN** deve mostrar o nome do objetivo
- **THEN** deve mostrar o progresso atual no formato "X / Y dias"

### Requirement: Calendário Semanal no Widget
O widget SHALL exibir os 7 dias da semana atual (Domingo a Sábado), indicando os dias já completados do Streak.

#### Scenario: Visualização de dias completados
- **WHEN** o objetivo tem um streak de 3 dias e hoje é Terça-feira
- **THEN** os ícones de Domingo, Segunda e Terça devem aparecer marcados como completos (✓)
- **THEN** os ícones de Quarta a Sábado devem aparecer como pendentes/futuros.

### Requirement: Atualização por Interação
O widget SHALL ser atualizado instantaneamente quando o usuário logar um progresso pelo aplicativo principal.

#### Scenario: Progresso logado no App
- **WHEN** o usuário abre o aplicativo e clica em completar a tarefa do dia
- **THEN** o aplicativo deve notificar o `GlanceAppWidgetManager`
- **THEN** o widget deve refletir o novo progresso imediatamente

### Requirement: Atualização Agendada
O widget SHALL ser atualizado automaticamente à meia-noite para avançar o dia atual da semana.

#### Scenario: Mudança de dia
- **WHEN** o relógio do sistema atinge 00:00
- **THEN** o `WorkManager` executa a tarefa agendada
- **THEN** o widget avança o marcador visual de "hoje" para o próximo dia da semana
