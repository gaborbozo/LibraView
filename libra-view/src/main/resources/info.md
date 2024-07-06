# Module referencing, loading

Defined with **Maven profiles**.

- By selecting a given profile, You ensure that the related package will be added to the dependencies of the
  _libra-view_ application.
- `config.ModuleConfig` defines scannable modules for the container. By convention keep that only `config` packages are
  scanned.
