For this Application we are following the MVVM Android Architecture. Along with that we have written the Unit test cases and Espresso UI Test for the same.

Below is the description of the files and their responsibilities.


base -> Base Folder which contains all the Essential base classes and import method declaration and then the sub-classes will extend these classes and override the
methods for the functionality.

BaseAdapter -> This is the Base class for the adapters an contains methods like :

1) getItemCount() -> will return the itemCount which is used by the recyclerView to display the elements.

2) getItem(int position) -> Will return the particular Item at given position.

3) setData(MutableLiveData<List<T>> data, LifecycleOwner lifecycleOwner) -> will set the mutable Data and also initialize the lifeCycleOwner.


BaseMVMFragment -> This is the base class for the Fragments and contains the required method for initializing the views.

 1) getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) -> helps to inflate the layout passed.

 2) createViewModel() -> helps to return the viewModel.

 3) getLayout() -> will return the Layout which needs to be inflated.

BaseViewHolder -> Base Class for the ViewHolder for Binding the data inside the RecyclerView.

 1) bind(T data) -> Binds the data to view.

 2) createHolder(ViewGroup parent, int layout) -> Creates the binding for provided layout.

BaseViewModel -> Base Class for the ViewModel used in the architecture and also contains the methods for showing the different state during inflation of
the view.

Data Part :-

model -> Contains the model classes which will help to map the data from API response to the required structure.

  1) BuiltBy -> Model class which has the response for the repo with their username and avtar images.

  2) Repository -> Model class which has all the required information used for displaying the data onto the UI.

repository -> Has the interface and its implementation for the Api call.

  1) TrendingDataRepository -> this is the interface which has the method for getting all the trending repositories.

  2) TrendingDataRepositoryImpl -> this is the implementation of the repository interface.

webservices -> Have the logic for calling the webServices which is used for fetching the API response.

     interceptor -> This is used to intercept the response and will decide whether we have to fetch the data from API or from the cache and also has the logic for defining the cache time limit.

Utils -> This is the class for checking the Active Network connection or not.


di part -> Contains all the module class for Dependency Injection Like Activity and Fragment Module for Activity and Fragment Injection.


view part -> Contains the adapter and Fragment class for displaying the data.

 TrendingBindingAdapter -> Data Binding class for binding the data inside the Fragment.

 TrendingListAdapter -> Adapter class which is extending the BaseAdapter class and provide the creating the binding the item inside the RecyclerView.

 TrendingListFragment -> Extending the BaseMVVMFragment and responsible for creating the viewModel and setting the data to the item.


viewModel part -> This is the class for all the logic for calling the repositories for fetching the trending the repositories and passing that data to the view part.



DemoApplication -> Base Application class for the Code.


MainActivity -> Launching and main activity of the application.