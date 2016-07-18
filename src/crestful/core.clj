(ns crestful.core
  (:gen-class)
  (:require [clojure.pprint :refer [pprint]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.params :refer [wrap-params]]
            [clojure.tools.cli :refer [parse-opts]]
            [clojure.tools.logging :as log])
  (:use ring.util.response
        ring.adapter.jetty))

(defn greeting [{params :params}]
  (response (str "hello, " (:name params))))

(defn datafoo [request]
  (response {:prop1 "hello" :prop2 "world"}))

(defn handler [request]
  (let [uri (:uri request)]
    (log/info (str "received request -> uri: " uri " params: " (:params request)))
    (or (if (= uri "/greeting") (greeting request))
        (if (= uri "/data") (datafoo request))
        (response "hey there"))))

(def cli-options
  [["-p" "--port PORT" "port to listen on"
    :parse-fn #(Integer/parseInt %)
    :default 8080]
   ["-h" "--help"]])

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main [& args]
  (let [options (parse-opts args cli-options)
        port (:port (:options options))]
    (if (:help (:options options)) (exit 0 (:summary options))
        (run-jetty (-> handler
                       wrap-keyword-params
                       wrap-params
                       wrap-json-response) {:port port}))))
