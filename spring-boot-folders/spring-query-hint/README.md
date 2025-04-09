## Spring QueryHints annotation 
-------------------------
## To optimize database peformance
		Indexing, Partitioning, Materialized views.

------------------------
@QueryHints({
		
  - @QueryHint(name="org.hibernate.readOnly", value="true"),
  - @QueryHint(name ="org.hibernate.fetchSize", value="100"),
  - @QueryHint(name="org.hibernate.cacheable", value="true"),
  - @QueryHint(name = "jakarta.persistence.cache.retrieveMode", value = "USE"),
  - @QueryHint(name = "jakarta.persistence.cache.storeMode", value = "USE"),
  - @QueryHint(name = "jakarta.persistence.query.timeout", value = "2000")
	}
)
------------------------	
QueryHints annotation-- Perform database performance and optimization in query

	===> Reduce Response time, lower database load, faster response

## Validation

### Before QueryHints : Taken time in 582ms
	HHH000117: HQL: [CRITERIA] select e1_0.id,e1_0.dept,e1_0.email,e1_0.gender,e1_0.name,e1_0.salary from employee e1_0 where e1_0.salary>?, time: 582ms, rows: 77644

### After  QueryHints : Taken time in 308ms
	2025-04-09T15:29:05.315-04:00 DEBUG 5896 --- [spring-query-hint] [nio-8080-exec-3] o.h.stat.internal.StatisticsImpl         
 : HHH000117: HQL: [CRITERIA] select e1_0.id,e1_0.dept,e1_0.email,e1_0.gender,e1_0.name,e1_0.salary from employee e1_0 where e1_0.salary>?, time: 308ms, rows: 77644
