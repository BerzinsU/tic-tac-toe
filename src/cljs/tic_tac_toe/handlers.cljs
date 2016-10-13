(ns tic-tac-toe.handlers
  (:require [re-frame.core :as rf]
            [tic-tac-toe.db :as db]
            [tic-tac-toe.subs :as sb]))

(rf/reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(defn- switch-player [db]
  (let [active-player- (sb/get-active-player-)]
    (assoc-in db [:active-player] (quot 2 @active-player-))))

(rf/reg-event-db
  :tile-clicked
  (fn [db [_ id]]
    (let [active-player- (sb/get-active-player-)]
      (-> (assoc-in db [:tiles id :clicked] @active-player-)
          (switch-player)))))

(rf/reg-event-db
  :reset-board
  (fn [db [_]]
    (->
      (assoc-in db [:tiles] nil)
      (assoc-in [:active-player] 1))))