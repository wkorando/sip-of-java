```java
public class AccountsPayableRepoImpl 
   implements AccountsPayableRepo {

	...
	public void save(AccountsPayableRecord apr) 
	...
	public List<AccountsPayableRecord> findAll() 
	...
	public Optional<AccountsPayableRecord> findById(Long id) 
 	...
 	
	/**
	 * Look up a {@index APR} by its legacy id.
	 * @hidden
	 * @param id
	 * @return
	 */
	Optional<AccountsPayableRecord> 
	  findByOldbId(Integer id){
		//TO-DO Implement
	}
}
```