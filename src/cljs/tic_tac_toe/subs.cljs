(ns tic-tac-toe.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]
            [re-frame.db :refer [app-db]]
            [reagent.ratom :as ra]))

(defn- game-state [db]
  (get-in db [:game-state]))

(defn game-state- []
  (reaction (game-state @app-db)))

(defn- get-player [db id]
  (get-in db [:players id]))

(defn get-player- [id]
  (reaction (get-player @app-db id)))

(defn- player-score [db id]
  (:score (get-player db id)))

(defn player-score- [id]
  (reaction (player-score @app-db id)))

(defn- get-active-player [db]
  (get-in db [:active-player]))

(defn get-active-player- []
  (ra/reaction (get-active-player @app-db)))

(defn- tile [db id]
  (get-in db [:tiles id]))

(defn tile- [id]
  (reaction (tile @app-db id)))

(defn- tile-clicked? [db id]
  (get-in db [:tiles id :clicked]))

(defn tile-clicked?- [id]
  (reaction (tile-clicked? @app-db id)))


(defn mark-color []
  (get-in @app-db [:mark-color]))

(defn- tile-marked? [db id]
  (get-in db [:tiles id :mark]))

(defn tile-marked?- [id]
  (reaction (tile-marked? @app-db id)))