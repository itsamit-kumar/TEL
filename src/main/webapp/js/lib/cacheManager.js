var CacheManager = (function() {
	var CMInstance;

	function createCacheManager() {
		
		/*
		 * Configuration
		 * 
		 */
		var storage = sessionStorage; // Reference to the sessionStorage
		var defaultHitVal = 1; // Default hit value
		var clearIntervalTime = 10*1000; //Interval Time to check the expired items
		var cacheLimit = 1024 * 1024 * 5; // 1 MB
		
		/* * ======== Start : Add for clearance Sonar Issue 31 July 2017 ======	 * */
		
		/*
		 * Returns the available space for storage 
		 * @param
		 * @returns space(Number) in bytes
		 */
		var getSpace = function(){
			var remSpace = cacheLimit - unescape(encodeURIComponent(JSON.stringify(storage))).length;
			return remSpace;
		};
		/*
		 * Removes one oldest item based on close expireTime
		 * @param
		 * @return
		 */
		var removeOldestData=function(){
			var dataArr = []; 
			var obj, key; //---SonarQube Issue 31 July 2017 7
			for ( var i = 0; i < storage.length; i++) {
				key = storage.key(i);
				obj = toJSONObj(storage.getItem(key));
				obj.key = key;
				dataArr.push(obj);
			}
			dataArr.sort(function (a, b) {
				  if (a.expireTime > b.expireTime) {
					    return 1;
					  }
				  if (a.expireTime < b.expireTime) {
					    return -1;
				   }
				   return 0;
			});
			storage.removeItem(dataArr[0].key);
			//console.log("old items removed..."+dataArr[0].key);
			
		};
		/*
		 *Free the space from cache required to store the new data 
		 * @param data as String
		 * @returns 
		 */
		
		var createSpace=function(data){
			var dataSize = unescape(encodeURIComponent(data)).length;
			//console.log(getSpace()+"___d___"+dataSize);
			if(getSpace()<dataSize){
				while(getSpace()<dataSize){
					removeOldestData();
				}
			}else{
				//console.log("Has Enough Space.."+Number(getSpace()/(1024*1024)));
			}
			
		};
		/*
		 * Formats the data and saves to storage
		 * @param id as String, obj as Object, timeToLive as int, hits as int
		 * @returns 
		 */
		var saveDataToStorage = function(id, obj, timeToLive, hits) {
			var d = new Date();
			var t = d.getTime() + (timeToLive * 1000);
			var dataObj = {
				value : toJSONStr(obj),
				expireTime : t,
				hits : hits
			};
			
			var data = toJSONStr(dataObj);
			createSpace(data);
			storage.setItem(id, data);
			
		};
		/*
		 * Takes the KeyName and Data as string and rewrites to storage
		 * @param id as String, data as String
		 * @returns
		 */
		var updateDataToStorage = function(id, data) {
			storage.setItem(id, data);
		};
		/*
		 * Retrieves the data from storage and converts it to objects 
		 * @param id as String
		 * @return Object
		 */
		var getDataFromStorage = function(id) {
			var retObj;
			var str = storage.getItem(id);
			if (str) {
				retObj = toJSONObj(str);
			} else {
				retObj = null;
			}
			return retObj;
		};

		/* * ======== Ends  : Add for clearance Sonar Issue 31 July 2017 ======	 * */
		
		/*
		 * Adds single item to storage 
		 * @param id as String, obj as Object, timeToLive as int(seconds) 
		 * @returns
		 */
		var insert = function(id, obj, timeToLive) {
			saveDataToStorage(id, obj, timeToLive, defaultHitVal);
		};

		/*
		 * Adds multiple items to storage
		 * @param objs as Array[Objects], idAttr as String, timeToLive as int(seconds)
		 * @returns 
		 */
		var insertObjects = function(objs, idAttr, timeToLive) {
			var id, obj;
			for ( var i = 0; i < objs.length; i++) {
				obj = objs[i];
				id = obj[idAttr];
				saveDataToStorage(id, obj, timeToLive, defaultHitVal);
			}

		};
		/*
		 * Takes the key name and returns the value as object
		 * @param id as String
		 * @returns Object
		 */
		var get = function(id) {
			var retObj = null;
			var obj = getDataFromStorage(id);
			var currentTime = new Date().getTime();
			if (obj && obj.expireTime > currentTime) {
				obj.hits = Number(obj.hits) + 1;
				var data = toJSONStr(obj);
				updateDataToStorage(id, data);
				retObj = toJSONObj(obj.value);
			}
			return retObj;
		};
		/*
		 * Takes the input as two dimensional Array[Key Name][empty], returns values filled in the second column 
		 * @param objsArr[][] as 2 dimensional Array
		 * @returns 2 dimensional Array
		 */
		var getObjects = function(objsArr) {
			var newObjsArr = objsArr.slice();
			var currentTime = new Date().getTime();
			var id, obj;
			for ( var i = 0; i < objsArr.length; i++) {
				id = objsArr[i][0];
				obj = getDataFromStorage(id);
				if (obj && obj.expireTime > currentTime) {
					obj.hits = Number(obj.hits) + 1;
					var data = toJSONStr(obj);
					updateDataToStorage(id, data);
					newObjsArr[i][1] = toJSONObj(obj.value);
				}
			}
			return newObjsArr;
		};
		//---SonarQube Issue 31 July 2017 1 saveDataToStorage
		//---SonarQube Issue 31 July 2017 5 updateDataToStorage
		//---SonarQube Issue 31 July 2017 6 getDataFromStorage		
		/*
		 * Converts JSON string to Object
		 * @param str as String
		 * @returns Object
		 */
		var toJSONObj = function(str) {
			return JSON.parse(str);
		};
		/*
		 * Converts to object to JSON string  
		 * @param obj as Object
		 * @returns String
		 */
		var toJSONStr = function(obj) {
			return JSON.stringify(obj);
		};
		/*
		 * Removes the expired items from the storage
		 * @param
		 * @return 
		 */
		var removeExpiredData = function() {
			var obj, key;
			var currentTime = new Date().getTime();
			var expKeys = []; 
			for ( var i = 0; i < storage.length; i++) {
				key = storage.key(i);
				obj = toJSONObj(storage.getItem(key));
				if (obj.expireTime <= currentTime) {
					expKeys.push(key);
				}
			}
			for(var j = 0; j<expKeys.length;j++){
				storage.removeItem(expKeys[j]);
			}

		};
		
		//---SonarQube Issue 31 July 2017 2 createSpace
		//---SonarQube Issue 31 July 2017 4 removeOldestData
		
		//---SonarQube Issue 31 July 2017 3 getSpace
		
		/*
		 * Sets the interval for calling remove expired data
		 */
		//var periodInt = setInterval(removeExpiredData, clearIntervalTime);
		
		/*
		 * Public methods
		 */
		return {
			insert : insert,
			insertObjects : insertObjects,
			get : get,
			getObjects : getObjects
		};

	}

	return {
		getInstance : function() {
			if (!CMInstance) {
				CMInstance = createCacheManager();
			}
			return CMInstance;
		}
	};

})();
