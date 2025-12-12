# LocalChat

A simple local chat Minecraft Bukkit plugin. Messages sent with LocalChat are only visibile to nearby players. 

Compatible with Folia.

## Usage
`message-range` is set to 100 blocks by default, but can be changed in the config. If 'dynamic-range' is enabled then the maximum distance for receiving local messages will be based on the current `view-distance` in server.properties.

### Send LocalChat message
`/local (message)` or `/l (message)`

Send a message locally

### Toggle LocalChat

`/localtoggle`

Toggle LocalChat. If toggled on, all messages sent will be sent locally (without needing to use `/local`)
