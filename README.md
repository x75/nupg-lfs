# nuPg

The New Pulsar Generator

(c) Marcin Pietruszewski, 2020 and before

nuPg is a new Pulsar Synthesis environment for SuperCollider,
superceding / complementing the original SuperCollider 1/2 (?) Pulsar
Generator by Curtis Roads. Pulsar synthesis due to Curtis Roads et al
[citation needed]. Check up on the theory if you real.

nuPg is distributed as SuperCollider Standalone App for Mac and
possibly Win [citation needed] and can be obtained from here [citation needed]. Linux support is WIP and this README
is part of the effort to run nuPg on Linux.

Good news: working, in both nuPg versions 1.0 and 2.0 and in
SuperCollider versions SC 3.9 and SC 3.11.

## Dependencies

nuPg depends on a few standard SuperCollider Extension that are all available as Quarks.

Please make sure to install all required Quarks before trying to run nuPg.

Refer to existing documentation [citation needed, WIP].

## Linux install notes

1/ Clone the repository from https://github.com/x75/nupg-lfs

`git clone git@github.com:x75/nupg-lfs.git`

2/ Change into the cloned repository

`cd nupg-lfs`

3/ Within that directory check out the x75-1 branch

`git checkout x75-1`

There might be something about git-lfs needed to do here when cloning the first time.

4/ Remove all NuPG and NuWS files and directories from previous installs in  `~/.local/share/SuperCollider/Extensions/`

5/ Install nuPg by copying the class files into the user Extension folder

Make sure there is a nuPg2.0 directory in your SC user library,

``` bash
mkdir -p $HOME/.local/share/SuperCollider/Extension/Nu_PG_2.0
```

The Exact spelling of the folder name doesn't matter, SuperCollider is agnostic when loading stuff on init.

Then 'install' by copying all nuPg stuff into the SC user extension library, with

``` bash
rsync -av --progress DATA_STRUCTURE FILES GUI SYNTHESIS TABLES ${HOME}/.local/share/SuperCollider/Extensions/Nu_PG_2.0/
```

## Startup

Launch the SuperCollider IDE and open the `startup-interactive.scd`
file which is on the top level of the repository.

Executing the code block in the startup file, the nuPg should GUI
open. Go to server window and press the 'boot' button. Start loading
your buffers.
