import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link AccountsPayableRepo}
 * 
 * @author bkorando
 * @since 1.0
 */
public class AccountsPayableRepoImpl implements AccountsPayableRepo {

	/**
	 * {@systemProperty username}
	 */
	private String username;
	/**
	 * {@systemProperty password}
	 */
	private String password;
	/**
	 * {@systemProperty jdbc.url}
     * @since 1.2
	 */
	private String jdbcUrl;
	
	/*
	 * (non-Javadoc)
	 */
	@Override
	public void save(AccountsPayableRecord apr) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public List<AccountsPayableRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public Optional<AccountsPayableRecord> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Look up a {@index APR} by its legacy id.
	 * @hidden
	 * @param id
	 * @return
	 */
	Optional<AccountsPayableRecord> findByOldbId(Integer id){
		return null;
	}
}
