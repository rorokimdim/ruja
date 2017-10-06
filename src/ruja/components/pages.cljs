(ns ruja.components.pages
  (:require [ruja.dao :refer [APP-STATE]]
            [kioo.reagent]
            [kioo.reagent :refer [content set-attr do-> substitute listen]])
  (:require-macros [kioo.reagent :refer [defsnippet deftemplate]]))

(deftemplate home-page "templates/home.html" [])

(defn handle-art-image-click [e]
  "Handles click on an art image."
  (let [element (.-currentTarget e)
        img-url (.-src element)
        big-img-url (.replace img-url "_small" "")]
    (set! js/window.location.href big-img-url)))

(deftemplate art-page "templates/art.html" []
  {[:img.sketch-button] (do-> (listen :on-click handle-art-image-click))})

(deftemplate research-page "templates/research.html" [])
(deftemplate publications-page "templates/publications.html" [])
(deftemplate cv-page "templates/cv.html" [])
