(ns tic-tac-toe.db
  (:require [re-frame.db :refer [app-db]]
            [re-frame.core :as rf]
            [reagent.ratom :as ra :include-macros true]))

(def default-db
  {:name          "tic-tac-toe"
   :active-player 1
   :players       {1 {:name  "Player 1"
                      :score 0
                      :color "green"}
                   2 {:name  "Player 2"
                      :score 0
                      :color "blue" }}})

