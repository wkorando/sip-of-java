import java.util.List;
import java.util.Optional;

/**
 * 
 * @author bkorando
 *
 */
public interface AccountsPayableRepo {

	/**
	 * Save a new {@link AccountsPayableRecord}
	 * @param {@link AccountsPayableRecord} 
	 */
	void save(AccountsPayableRecord apr);
	
	/**
	 * Find all {@link AccountsPayableRecord}'s
	 * @return
	 */
	List<AccountsPayableRecord> findAll();
	
	/**
	 * Lookup a {@link AccountsPayableRecord} by its id.
	 * @param id
	 * @return
	 */
	Optional<AccountsPayableRecord> findById(Long id);
}
