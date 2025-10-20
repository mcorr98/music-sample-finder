package cli;

import repository.ApiTrackRepository;
import repository.TrackRepository;
import service.TrackService;

public class App {

	public static void main(String[] args) {
	    String token = Config.getDiscogsToken();
	    TrackRepository repo = new ApiTrackRepository(token);
	    TrackService service = new TrackService(repo);

	    CliRunner runner = new CliRunner(service);
	    runner.run();
	}
}
