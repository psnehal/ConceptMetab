dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    //username = "sa"
    //password = ""
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
           //  dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			//url = "jdbc:mysql://10.16.26.199:3306/test?useUnicode=yes&characterEncoding=UTF-8"
			url = "jdbc:mysql://localhost/test?useUnicode=yes&characterEncoding=UTF-8"
			//url = "jdbc:mysql://127.0.0.1:33066/test?useUnicode=yes&characterEncoding=UTF-8"
			username = "snehal"
			password = "snehal"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
			url = "jdbc:mysql://localhost/test?useUnicode=yes&characterEncoding=UTF-8"
			//url = "jdbc:mysql://127.0.0.1:33066/test?useUnicode=yes&characterEncoding=UTF-8"
			username = "snehal"
			password = "snehal"
        }
    }
}
