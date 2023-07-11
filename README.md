# À propos du Connecteur Chatbots

* Licence : [AGPL v3](http://www.gnu.org/licenses/agpl.txt) - Copyright Région Nouvelle Aquitaine
* Propriétaire(s) : CGI
* Mainteneur(s) : CGI
* Financeur(s) : Région Nouvelle Aquitaine
* Description : Module permettant l'intégration d'un chatbot dans l'ENT

## Configuration du module chatbots dans le projet OPEN ENT

Dans le fichier 'ent-core.json.template' du projet OPEN ENT :

<pre>
    {
      "name": "fr.openent~chatbot~version",
      "config": {
        "main" : "fr.openent.chatbot.Chatbot",
        "port" : 8481,
        "app-name" : "Chatbot",
    	"app-address" : "/chatbot",
    	"app-icon" : "Chatbot-large",
        "host": "${host}",
        "ssl" : false,
        "auto-redeploy": false,
        "userbook-host": "${host}",
        "integration-mode" : "HTTP",
        "app-registry.port" : 8012,
        "mode" : "${mode}",
        "entcore.port" : 8009,
        "chatbot-url": "https://chatbot.example.fr"
      }
    }
</pre>

Le paramètre "chatbot-url" correspond à l'URL du chatbot.

Associer une route d'entrée à la configuration du module proxy intégré (`"name": "com.wse~http-proxy~1.0.0"`) :
<pre>
      {
        "location": "/chatbot",
        "proxy_pass": "http://localhost:8481"
      }
</pre>

# Gestion des droits

* "chatbot.view" : Accéder et afficher le chatbot

# Configuration Nginx

Dans le fichier de configuration nginx du serveur Chatbot, ajouter un header Content-Security-Policy pour permettre
l'affichage de l'iframe chatbot dans l'ENT.

<pre>
    Content-Security-Policy "frame-ancestors 'none' 'http://url-plateforme-ent.fr';"
</pre>


