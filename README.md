# tic-tac-toe

![Image of first proto]
(https://puu.sh/tk7fH/44f3a4b160.png)

A [re-frame](https://github.com/Day8/re-frame) application designed to play tic-tac-toe

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
