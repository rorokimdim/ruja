(ns ruja.core
  (:require
   [reagent.core :as reagent]
   [secretary.core :as secretary]
   [goog.events :as events]
   [goog.history.EventType :as HistoryEventType]
   [dommy.core :as dommy]

   [ruja.dao :refer [APP-STATE]]
   [ruja.components.root :refer [root-comp]])

  (:import goog.History)

  (:require-macros
   [secretary.core :refer [defroute]]
   [dommy.core :refer [sel sel1]]))

(enable-console-print!)

(defn hook-browser-navigation! []
  "Hooks with browser history navigation."
  (doto (History.)
    (events/listen
     HistoryEventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn change-selected-menu-item! [id]
  "Changes the selected menu item to ID."
  (doseq [todo (sel [:#menu :.item])]
    (dommy/remove-class! todo :selected-item))

  (dommy/add-class! (sel1 id) :selected-item))

(defn set-background-image! [page]
  (if (= page :home)
    (dommy/set-style! (sel1 :#body) :background-image "url('images/about.png')")
    (dommy/set-style! (sel1 :#body) :background-image "")))

(defn app-routes []
  "Defines url routes."""
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (swap! APP-STATE assoc :page :home)
    (set-background-image! (:page @APP-STATE))
    (change-selected-menu-item! :#home-link))

  (defroute "/art" []
    (swap! APP-STATE assoc :page :art)
    (set-background-image! (:page @APP-STATE))
    (change-selected-menu-item! :#art-link))

  (defroute "/research" []
    (swap! APP-STATE assoc :page :research)
    (set-background-image! (:page @APP-STATE))
    (change-selected-menu-item! :#research-link))

  (defroute "/publications" []
    (swap! APP-STATE assoc :page :publications)
    (set-background-image! (:page @APP-STATE))
    (change-selected-menu-item! :#publications-link))

  (defroute "/cv" []
    (swap! APP-STATE assoc :page :cv)
    (set-background-image! (:page @APP-STATE))
    (change-selected-menu-item! :#cv-link))

  (hook-browser-navigation!))

(reagent/render-component
 [root-comp (:page @APP-STATE)]
 (. js/document (getElementById "app")))

(app-routes)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! APP-STATE update-in [:__figwheel_counter] inc)
  )
