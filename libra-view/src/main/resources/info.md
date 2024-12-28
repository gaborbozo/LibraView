# Module defining, referencing

Defined with **Maven profiles**.

- By selecting a given profile, You ensure that the related package will be added to the dependencies of the
  _libra-view_ application.
- `config.ModuleConfig` defines scannable modules for the container. By convention, keep that only `config` packages are
  marked with `ComponentScan` in the core application.

---

# Database, migration

Flyway over Postgresql

- During the **install** phase of the build lifecycle, the **flyway:migrate** goal is executed.
- If the **clearMigration** profile is activated, the **flyway:clean** goal will be executed during the **clean**
  lifecycle phase.

> Currently, when the **clearMigration** profile is active, the **flyway:migrate** goal is not executed

---

# Remote data access

`RestClient` - fluent API of `WebClient` with the infrastructure of `RestTemplate`.

---

# Architecture, name conventions

### Modules

In the _Client_, _Service_, _Controller_, and _Config_ packages, each file must have a more descriptive and meaningful
name.