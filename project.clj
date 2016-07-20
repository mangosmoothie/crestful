(defproject crestful "0.1.0-SNAPSHOT"
  :description "clojure ring simple ws example"
  :url "http://github.com/mangosmoothie/crestful"
  :license {:name "The Unlicense"
            :url "http://unlicense.org"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.21"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler crestful.core/srvr}
  :main crestful.core)
