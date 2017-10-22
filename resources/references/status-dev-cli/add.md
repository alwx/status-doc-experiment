Adds a contact (DApp or bot).

```makefile
status-dev-cli add [contact] --ip [device ip]
```

`contact` — JSON containing contact information. It is not required if you develop a DApp and this DApp contains `package.json` file. Otherwise, this map should contain `whisper-identity`, `name` and `dapp-url` or `bot-url` fields.