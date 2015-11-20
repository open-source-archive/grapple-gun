# Grapple Gun

A toolbar to allow for seamless navigation between applications.
To add it to your page, just include the javascript:

```html
<script src="example.org/grapplegun.js"></script>
```

This is a work in progress.

## Development

First, install [boot](http://boot-clj.com/).
Then start the server:

```bash
./server.clj
```

Run the application in development mode:

```bash
boot dev
```

Optionally start a repl and connect it to the application running in the browser:

```bash
boot repl -c
(start-repl)
```
Test:

```bash
boot test
```

## License

Apache 2.0
