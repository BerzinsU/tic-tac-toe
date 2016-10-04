(ns tic-tac-toe.handlers
    (:require [re-frame.core :as re-frame]
              [tic-tac-toe.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
