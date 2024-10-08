# Architecture

## Name conventions

### Modules

In the _Client_, _Service_, _Controller_, and _Config_ packages, each file should have a more descriptive and meaningful
name than just a single word.

# Module

## Defining, referencing

Defined with **Maven profiles**.

- By selecting a given profile, You ensure that the related package will be added to the dependencies of the
  _libra-view_ application.
- `config.ModuleConfig` defines scannable modules for the container. By convention, keep that only `config` packages are
  marked with `ComponentScan` in the core application.

# Remote data access

`RestClient` - fluent API of `WebClient` with the infrastructure of `RestTemplate`.