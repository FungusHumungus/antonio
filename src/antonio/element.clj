(ns antonio.element
  (:require [t6.from-scala.core :refer [$ $$] :as $])
  (:import (scala.collection.immutable List)))


(defn observe
  [element value]
  (.observe element value))

(defn unobserve
  [element]
  (.unobserve element))

(defn setCondition
  [element condfn]
  (.setCondition element ($/fn [a] (condfn a)) ($ List/empty)))

(defn addCondition
  [element condfn]
  (.addCondition element ($/fn [a] (condfn a))) ($ List/empty))

(defn removeConditions
  [element]
  (.removeConditions element ($ List/empty)))

(defn setConstraint
  [element constfn]
  (.setConstraint element ($/fn [a] (constfn a))) ($ List/empty))

(defn addConstraint
  [element constfn]
  (.addConstraint element ($/fn [a] (constfn a))) ($ List/empty))

(defn removeConstraints
  [element]
  (.removeConstraints element ($ List/empty)))

(defmulti value type)

(defmethod value com.cra.figaro.language.Inject
  [inject]
  "The Scala Inject value returns a Scala list. We want to convert this to a vector."
  ($ (.value inject)
     reduce
     ($/fn [acc e] (conj (if (seq? acc) acc [acc]) e))))
