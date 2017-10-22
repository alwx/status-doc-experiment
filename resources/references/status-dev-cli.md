**status-dev-cli** is and additional set of tools for 3rd party developers. These tools allows to speed up the process of developing DApps and bots for Status.

## Requirements

* Node.js;
* NPM;
* [Watchman](https://facebook.github.io/watchman/docs/install.html).

## Installing

**Caution:** Depending on your OS `mdns` (one of `status-dev-cli` dependencies) might have some prerequisites. Make sure you follow advices from `mdns` [installation](https://github.com/agnat/node_mdns#installation) procedure.

```makefile
npm i -g status-dev-cli
```

## Commands

* [scan](/#/ref/status-dev-cli+scan)
* [add](/#/ref/status-dev-cli+add)
* [remove](/#/ref/status-dev-cli+remove)
* [watch](/#/ref/status-dev-cli+watch)
* [refresh](/#/ref/status-dev-cli+refresh)
* [switch-node](/#/ref/status-dev-cli+switch-node)
* [list](/#/ref/status-dev-cli+list)
* [log](/#/ref/status-dev-cli+log)

### Common parameters

Use `--ip <device-ip>` to specify your device's IP address. If you don't know your device's IP address, just run `status-dev-cli scan`. 
The IP should be provided for every command you try to execute (except `scan`).
Device IP can also be provided using the `STATUS_DEVICE_IP` environment variable, e.g. 

```makefile
STATUS_DEVICE_IP=192.168.0.2 status-dev-cli list
```

## DApp development

To make debugging work we run a web server on your device. It runs on port 5561 on both iOS and Android, but only if you need it.

To start a server you need to:

* Connect your device to computer;
* Open Status application and log in;
* Open `Console` chat and execute `/debug` command providing "On" as the argument.

You can also easily turn the server off from here.

**Note:** if you turn the server on, it will start automatically the next time you log in.

### Scenario

Imagine you are developing a DApp on your computer. You have a directory where all DApp files are placed, 
and there is a server running on your computer. Let's say it is running on port 8080, so you can access 
your DApp by typing http://localhost:8080 in your browser.

* Find the IP address of your device by running `status-dev-cli scan`;
* Add a DApp to Status by executing the following:

```makefile
status-dev-cli add '{"whisper-identity": "dapp-test", "dapp-url": "http://localhost:8080/", "name": "My Dapp"}' --ip <DEVICE IP>
```

* Open the "My Dapp" on your device;
* Optional: Execute this line to start automatically refreshing your DApp in Status browser when you change the DApp's code:

```makefile
status-dev-cli watch-dapp . '{"whisper-identity": "dapp-test"}' --ip <DEVICE IP>`
```

## Using status-dev-cli as a library

![snippets/status-dev-cli-library javascript]