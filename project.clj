(defproject tic-tac-toe "0.1.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [reagent "0.6.0"]
                 [binaryage/devtools "0.8.2"]
                 [re-frame "0.8.0"]]

  :plugins [[lein-cljsbuild "1.1.4"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["docs/js/compiled" "target"]

  :figwheel {:css-dirs ["docs/css"]
             :server-port 8980}

  :profiles
  {:dev
   {:dependencies []

    :plugins      [[lein-figwheel "0.5.7"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "tic-tac-toe.core/mount-root"}
     :compiler     {:main                 tic-tac-toe.core
                    :output-to            "docs/js/compiled/app.js"
                    :output-dir           "docs/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            tic-tac-toe.core
                    :output-to       "docs/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    ]}

  )
