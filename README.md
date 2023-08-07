# explorer_ma_ville
Titre du Projet : Explorer Ma Ville

Description :
L'application "Explorer Ma Ville" est conçue pour permettre aux utilisateurs de découvrir et d'explorer divers lieux d'intérêt à proximité de leur position actuelle. L'application utilise la géolocalisation de l'appareil pour afficher une carte interactive, marquant les lieux d'intérêt à proximité. Elle offre également la possibilité de consulter une liste détaillée de ces lieux, fournissant des informations telles que le nom, la localisation, l'adresse et les catégories des lieux.

Techniques Utilisées :

Android Studio : L'application est développée à l'aide de l'environnement de développement Android Studio, qui fournit une interface conviviale pour la création d'applications Android.

Kotlin : Le langage de programmation Kotlin est utilisé pour développer l'application Android. Kotlin est moderne, expressif et sûr, ce qui en fait un choix populaire pour le développement d'applications Android.

Google Maps API : L'application utilise l'API Google Maps pour afficher une carte interactive à l'écran. Les fonctionnalités telles que l'affichage de la position actuelle de l'utilisateur, l'ajout de marqueurs pour les lieux d'intérêt et le zoom sont implémentées à l'aide de l'API Google Maps.

Foursquare API :Cette API permet de récupérer des données telles que les noms, les adresses, les catégories et les détails spécifiques des lieux, ce qui enrichit l'expérience de l'utilisateur en fournissant des informations utiles sur les endroits à explorer

Retrofit : Retrofit est une bibliothèque qui facilite les appels à des API REST. Dans ce projet, Retrofit est utilisé pour communiquer avec l'API Foursquare afin de récupérer des informations sur les lieux d'intérêt.

RecyclerView : La liste des lieux d'intérêt est affichée à l'aide d'un RecyclerView, qui offre une liste déroulante optimisée pour afficher de grandes quantités de données.

Capteur d'Accéléromètre : Un capteur d'accéléromètre est utilisé pour détecter les secousses du téléphone. Lorsqu'une secousse est détectée, l'application passe à l'activité "ListedActivitiesActivity", affichant les lieux d'intérêt à proximité.

Intents : Les Intents sont utilisés pour passer d'une activité à une autre, permettant à l'utilisateur de basculer entre la carte et la liste des activités.

Styles et Thèmes : Les styles et les thèmes sont utilisés pour personnaliser l'apparence de l'application, y compris la couleur de l'interface utilisateur, les polices et les éléments visuels tels que les barres d'outils.
