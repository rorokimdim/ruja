(ns ruja.components.root
  (:require [ruja.components.pages :as pages]
            [ruja.dao :refer [APP-STATE]])
  (:require-macros [kioo.reagent :refer [defsnippet deftemplate]]))


(defn root-comp []
  "Builds root component."
  (case (:page @APP-STATE)
    :home    [pages/home-page]
    :art    [pages/art-page]
    :cv    [pages/cv-page]
    :research [pages/research-page]
    :publications [pages/publications-page]
    [pages/home-page]))
