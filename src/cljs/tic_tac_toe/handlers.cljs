(ns tic-tac-toe.handlers
  (:require [re-frame.core :as rf]
            [tic-tac-toe.db :as db]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(rf/reg-event-db
  :tile-clicked
  (fn [db [_ id]]
    (assoc-in db [:tiles id :clicked] true)))

(rf/reg-event-db
  :reset-board
  (fn [db [_]]
    (assoc-in db [:tiles] nil)))