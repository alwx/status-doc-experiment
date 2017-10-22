**status-dev-cli 2.2.0+, Status 0.9.4+**

Switches network.

Typically when developing DApps, a developer uses his own private chain or a simulator.
Status inserts its own web3 object into the DApp, however, this web3 object is connected to a different network than the development one.
This command allows to switch a network. Next time you login the network will be switched back.

```makefile
status-dev-cli switch-node <url> --ip [device ip]
```

`url` (required) — the network that will be used instead of `http://localhost:8545`