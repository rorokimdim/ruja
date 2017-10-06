(ns ruja.dao
  (:require
   [reagent.core :as r]))

(defonce APP-STATE (r/atom {:page "Home"}))
