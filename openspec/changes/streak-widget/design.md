## Context

O aplicativo `StreakDays` atual rastreia metas contínuas (streaks), permitindo aos usuários acompanhar o número de dias seguidos que realizaram uma atividade. Para aumentar o engajamento sem que o usuário precise abrir o aplicativo o tempo todo, decidimos implementar um App Widget para a tela inicial utilizando Jetpack Glance.

O Jetpack Glance permite o desenvolvimento de widgets usando um modelo declarativo similar ao Jetpack Compose, o que se alinha com a stack atual do aplicativo (UI feita em Compose).

## Goals / Non-Goals

**Goals:**
- Implementar um widget funcional na tela inicial do Android.
- O widget deve exibir o estado do principal/último objetivo modificado.
- Exibir uma visualização semanal de progresso ("checkmarks" nos dias da semana).
- Atualizar a UI do widget com base em interações do usuário (app principal) e atualizações programadas (virada de dia).

**Non-Goals:**
- Não haverá lista rolável com múltiplos objetivos dentro do widget (para simplificar a versão inicial).
- O widget não suportará ações complexas como editar ou apagar o objetivo.

## Decisions

1. **Jetpack Glance vs RemoteViews:**
   **Decisão:** Utilizar Jetpack Glance.
   **Motivação:** A base de código atual já utiliza Jetpack Compose. O Glance fornece uma API amigável e moderna, evitando a manipulação direta do XML em `RemoteViews`.

2. **Gerenciamento de Atualização:**
   **Decisão:** Uma abordagem híbrida com `GlanceAppWidgetManager` e `WorkManager`.
   **Motivação:** Quando o usuário loga progresso pelo app principal, precisamos atualizar o widget instantaneamente via `GlanceAppWidgetManager`. Para a mudança natural de dias da semana (à meia-noite), utilizaremos um worker do `WorkManager` agendado diariamente para forçar a atualização visual de quem é "hoje".

3. **Seleção do Objetivo:**
   **Decisão:** O widget inicialmente tentará carregar o objetivo com a data de alteração mais recente ou o primeiro da lista do banco de dados (o mais ativo). Se a base de dados crescer, podemos adicionar um *Widget Configuration Activity* no futuro.
   **Motivação:** Para a primeira versão, pegar o objetivo mais relevante simplifica a implementação.

## Risks / Trade-offs

- **[Risco] Atualizações Atrasadas pelo Sistema:** O SO do Android frequentemente restringe atualizações de widget para salvar bateria.
  **Mitigação:** Dependeremos do `WorkManager` para a atualização periódica diária e da chamada explícita de `update` pelo `GlanceAppWidgetManager` de dentro da MainActivity / ViewModel quando uma ação de usuário de fato ocorrer.

- **[Risco] Lógica de Cálculo da Semana:** Calcular quais dias passados da semana atual estão "cobertos" pelo `currentStreakDays` exige precisão no cálculo de datas.
  **Mitigação:** Escrever testes unitários exaustivos para a função de utilidade que fará essa conversão (de `currentStreakDays` para `List<Boolean>` da semana).
