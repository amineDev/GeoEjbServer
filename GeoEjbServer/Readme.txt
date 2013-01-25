Instructions obligatoire à faire :

1 - Pour que le logging fonctionne sous glassfish :
    Il faut récupérer les fichiers .jar dans le répertoire lib du projet et les mettre dans l'emplacemment
    de votre serveur glassfish  (glassfish/lib)
    
2 - Il faur créer une Datasource dans l'administration de glassfish pour pouvoir utiliser une DataSource
    Mysql car par default glassfish  utilise Derby comme DataSource
    
    Donc faire  les étapes suivantes :  
    
    - Aller dans la console de l'administration de glassFish http://localhost:4848
    
    - Aller dans le menu à gauche Ressources/JDBC
    
    - Click sur Connection Pools puis New
    
    - Entrer Un nom ex: Mysql
    
    -Puis Select javax.sql.ConnectionPoolDataSource dans ‘Resource Type’ 
     et  MySQL dans  ‘Database vendor’, puis click sur Next
     
    - puis remplir les informations 
    
    a. DatabaseName
    b. Password
    c. URL (jdbc:mysql://localhost/geonotes)
    d. Url (jdbc:mysql://localhost/geonotes)
    e. ServerName
    f. User
    
    - Puis click sur finish , vous serez rediriger vers la page connection Pools , click sur 
      la connection Pool que vous venez de créer  puis il faut tester que la connection marche bien
      en cliquant qur Ping (Un message va s'afficher)
      
    - Maintenant dans le menu JDBC , click sur JDBC Ressources puis New
    
    - Entrer le JNDI name pour votre data source : jdbc/geonotesDS (NB : si vous mettez un autre nom vous devez le modifiez dans persitance.xml  )
    
    - puis selectionnez la connection pool que vous avez crée , dans l'exemple que j'ai donner sa sera Mysql
    
    - puis click sur finish et c'est terminer :)