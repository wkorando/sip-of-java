import java.util.List;
import java.util.Optional;

/**
 * 
 * @author bkorando
 * @since 1.0
 */

public interface AccountsPayableRepo {

	/**
	 * Save a new {@link AccountsPayableRecord}
	 * 
	 * @param {@link AccountsPayableRecord}
	 * @since 1.0
	 * @deprecated don't save stuff!
	 */
	@Deprecated(since = "1.1")
	void save(AccountsPayableRecord apr);

	/**
	 * Find all {@link AccountsPayableRecord}'s
	 * 
	 * @return
	 * @since 1.0
	 */
	@Deprecated(since="1.1",forRemoval=true)
	List<AccountsPayableRecord> findAll();

	/**
	 * Lookup a {@link AccountsPayableRecord} by its id.
	 * 
	 * @param id
	 * @return
	 * @since 1.2
	 */
	Optional<AccountsPayableRecord> findById(Long id);
}
