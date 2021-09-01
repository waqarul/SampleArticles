# Sample Articles
This project make use of API to fetch Most Popular Articles from [New York Times.](https://developer.nytimes.com/get-started)

### Tech Stack
The following are used in the project:
-  MVVM Architecture
-  Lifecycle Aware Components
-  Coroutines
-  RxJava
-  Constraint-Layout
-  Navigation Drawer
-  Butter-knife
-  Retrofit
-  Moshi
-  Glide

### Flavours
The project contain 3 flavors that can be used to configure different environments:
- Development
- Staging
- Production

The following things are configured in flavors:
- App Name
- Base Url
- Url Path
- Server Version
- Api Key
- Enable/Disable Logs

The above configurations are done in:
**_<project_path>/app/build.gradle_**


### Articles by Period(1,7 or 13 days)
The function
```fun getArticles(period: String): Observable<BaseDataModel<List<ArticleModel>>>```
in **_ArticlesInteractor.kt_** class is configurable with period days i.e. **1, 7 & 13 days**.


### Installation & Building
You can download the source-code in following ways:
#### Method: 1
**HTTPS**: Use Git or checkout with SVN using the web URL.
```https://github.com/waqarul/SampleArticles.git```

#### Method: 2
**SSH**: Use a password-protected SSH key.
```git@github.com:waqarul/SampleArticles.git```

#### Method: 3
**Github CLI**: Clone the repository by
```gh repo clone waqarul/SampleArticles```


After downloading the source code:
- Checkout master branch
- Open Android Studio
- Goto to _File -> Open_ in menu bar.
- Navigate to <PATH_TO_PROJECT>/SampleArticles

When the project is setup then:
- Goto to _Build -> Rebuild_ in menu bar.
- After successful build, run the project by pressing **_PLAY_** button in toolbar or
- Goto to _Run -> Run 'app'_ in menu bar.