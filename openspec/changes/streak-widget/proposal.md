## Why

Adicionar um widget à tela inicial é uma excelente forma de manter o usuário engajado e constantemente lembrado do seu objetivo atual. Isso permite que os usuários acompanhem o progresso dos seus streaks sem precisarem abrir o aplicativo, aumentando a visibilidade das metas e incentivando a consistência diária.

## What Changes

- Criação de um App Widget usando Jetpack Glance.
- O widget exibirá:
  - O nome do objetivo em destaque.
  - O progresso do objetivo (Dias completados vs Dias da meta geral).
  - Uma barra de progresso horizontal para a meta de longo prazo.
  - Uma visão dos 7 dias da semana (Domingo a Sábado), marcando quais dias da semana atual foram concluídos com base no `currentStreakDays`.
- Implementação de um `GlanceAppWidgetReceiver` para gerenciar as atualizações do widget.
- Configuração de um `WorkManager` para agendar atualizações diárias (à meia-noite) para manter o indicador do "dia atual" da semana sempre correto.
- Integração do clique no widget para abrir o aplicativo na tela do dashboard.

## Capabilities

### New Capabilities
- `home-widget`: Adição de um widget para a tela inicial utilizando Jetpack Glance para exibir informações sobre o progresso e streaks.

### Modified Capabilities
- N/A

## Impact

- **Módulos Afetados:** Módulo `app`.
- **Dependências Novas:** Será necessário adicionar a dependência do Jetpack Glance (`androidx.glance:glance-appwidget`).
- **Permissões:** Nenhuma permissão nova no `AndroidManifest.xml` além das declarações padrões do App Widget (`<receiver>` e metadata do XML do widget).
- **Outros:** O `GoalsViewModel` ou o repositório de metas precisará fornecer uma maneira de acessar o "objetivo mais recente" ou o "objetivo principal" de forma rápida, já que o widget roda em um processo que fará leitura da fonte de dados local (provavelmente Room DataStore).
