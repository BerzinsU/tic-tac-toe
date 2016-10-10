(ns tic-tac-toe.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]
              [re-frame.db :refer [app-db]]))


(defn- get-player [db id]
  (get-in db [:players id]))

(defn get-player- [id]
  (reaction (get-player @app-db id)))

(defn- tile-clicked? [db id]
  (get-in db [:tiles id :clicked]))

(defn tile-clicked?- [id]
  (reaction (tile-clicked? @app-db id)))