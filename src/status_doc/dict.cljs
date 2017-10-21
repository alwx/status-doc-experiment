(ns status-doc.dict
  (:require-macros [status-doc.web :refer [defguides defrefs]]))

(defguides guides ["1-hello-world-bot"
                   "2-parameter-boxes"
                   "3-custom-previews"
                   "4-validators"
                   "5-mixable-and-global-bots"
                   "6-command-scopes"
                   "7-requests-and-responses"
                   "8-accessing-web3"
                   "9-your-first-dapp"
                   "10-custom-buttons-for-dapps"
                   "11-dapps-with-commands"
                   "12-localization"
                   "13-status-dev-cli"])

(defrefs refs [{:id       "command"
                :children [{:id "name"}
                           {:id "title"}
                           {:id "description"}]}])