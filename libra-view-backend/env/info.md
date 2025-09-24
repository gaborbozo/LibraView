# Database, migration

Flyway over Postgresql

- During the **install** phase of the build lifecycle, the **flyway:migrate** goal is executed.
- If the **clearMigration** profile is activated, the **flyway:clean** goal will be executed during the **clean**
  lifecycle phase.