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

1/ go to https://github.com/x75/nupg-lfs

2/ clone repo

3/ cd into repo dir, from within repo

4/ install

make sure there is a nuPg2.0 directory in your SC user library

``` bash
$ cd
$ mkdir -p ${HOME}/.local/share/SuperCollider/Extension/Nu_PG_2.0
```

Exact spelling of the folder name (WIP) doesn't matter, just need to stick to it, SuperCollider is agnostic when loading stuff on init.

Then 'install' by copying all nuPg stuff into the SC user extension library, with

``` bash
rsync -av --progress DATA_STRUCTURE FILES GUI SYNTHESIS TABLES ${HOME}/.local/share/SuperCollider/Extensions/Nu_PG_2.0/
```

## Startup

Loading nuPg is easy, jut copy the class code into your extensions dir, see above.

Running it requires to create couple of classes in the right way and in the right sequence order.

Other people have thought about it and it can be done. `startup.scd`, Stay tuned, WIP.


## temp
### slack transcript WIP

pseudo stuck, so just flushing. my work dir is on

https://github.com/x75/nupg-lfs [1]

remove all NuPG and NuWS files and dirs from  ~/.local/share/SuperCollider/Extensions/Nu_PG_2.0/ if you have a previous install

if you have all the quarks installed from previous you're all good, leave them. if not, install them. TODO: list of quarks. it's somewhere in the nupg docs themselves

```
$ git clone git@github.com:x75/nupg-lfs.git
$ cd nupg-lfs
$ git checkout x75-1
$ something about git-lfs might be needed here after on first clone, ymmv
$ mkdir ~/.local/share/SuperCollider/Extensions/Nu_PG_2.0
$ rsync -av DATA_STRUCTURE FILES GUI SYNTHESIS TABLES ~/.local/share/SuperCollider/Extensions/Nu_PG_2.0/
```

scusa :shrug:

launch the SC IDE and, the startup-interactive.scd file which is on the top level in the repository

execute the startup code block, nupg GUI opens, got to server window, press boot button, start loading your buffers

works like a charm for me, let me know how it goes

[1] @marcin wanted to use git-lfs to store the wav/aiff of TABLES etc. your repo is not setup for lfs, so i couldn't push to my fork of your repo (github shmoo?). you need to setup upstream for lfs to fix that, can be done

