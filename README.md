# LocalChat

A simple local chat Minecraft Bukkit plugin. Messages sent with LocalChat are only visibile to nearby players. 

Compatible with Folia.

<img width="1012" height="118" alt="image" src="https://github.com/user-attachments/assets/4b8c31b4-33fc-440e-8505-f59d25cadcc4" />

## Usage

### Send LocalChat message
`/local (message)` or `/l (message)`

Send a message locally

### Toggle LocalChat

`/localtoggle`

Toggle LocalChat. If toggled on, all messages sent will be sent locally (without needing to use `/local`)

## Config
`message-range` is set to 100 blocks by default, but can be changed in the config. 

If 'dynamic-range' is enabled then the maximum distance for receiving local messages will be based on the current `view-distance` in server.properties and `message-range` will be ignored.

This prefix and colour of local messages can be changed in the config.
