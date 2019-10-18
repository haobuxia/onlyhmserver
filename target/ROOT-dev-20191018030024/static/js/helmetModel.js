var animate = function () {
    requestAnimationFrame(animate);//循环调用刷新,在帧变化的时候
    helmetModel.renderer.render(helmetModel.scene, helmetModel.camera);
}

var helmetModel = {
    camera: null,
    scene: null,
    renderer: null,
    containerId:null,
    container: null,
    control: null,
    helmet: null,
    containerWidth: 300,
    containerHeight: 400,
    minDistance: 1,
    maxDistance: 10000
};

helmetModel.setupModel = function (containerId,enableControl) {
    console.debug("3d头盔初始化...");
    if(containerId == this.containerId){
        console.debug("头盔模型已经初始化过,不再重复初始化."+containerId);
        return;
    }

    this.containerId = containerId;
    this.container = $("#" + containerId);
    this.reCalSize();
    this.camera = new THREE.PerspectiveCamera(45, this.containerWidth / this.containerHeight, this.minDistance, this.maxDistance);//
    //对象的局部位置 camera的xyz坐标位置.
    this.camera.position.set(0, -5000, 300);//x由大变小则头盔右移.0居中；y由大变小则头盔图像变小，-5000正合适；z由大变小则头盔上移，0居中。

    // 对象局部旋转，单位为弧度
    //this.camera.quaternion

    //对象的局部缩放因子
    //this.camera.scale  默认{x: -1, y: 1, z: 1}
    this.camera.up.set(0, 0, 1);//空间的向上方向
    this.camera.lookAt(new THREE.Vector3(0, 0, 0));//旋转模型以面对观察点,左右上下位置微调 camera面向的位置

    this.scene = new THREE.Scene();
    this.loadStl();//载入stl模型
    // this.loadObj();//载入obj模型

    // lights
    this.scene.add(new THREE.AmbientLight(0x333333));//环境光

    var pointLight = new THREE.PointLight( 0xFFFF00, 0.8 );//点光源
    this.camera.add( pointLight );
    this.scene.add( this.camera );

    this.scene.fog = new THREE.Fog(0xffffff, this.minDistance, this.maxDistance);//这个类包含定义线性雾的参数，也就是说，密度随着距离的增加呈线性增长
    // renderer
    this.renderer = new THREE.WebGLRenderer({antialias: true});
    this.renderer.setClearColor(this.scene.fog.color);
    this.renderer.setSize(this.containerWidth, this.containerHeight);
    this.renderer.gammaInput = true;
    this.renderer.gammaOutput = true;
    this.renderer.shadowMapEnabled = true;
    this.renderer.shadowMapCullFace = THREE.CullFaceBack;
    this.container[0].appendChild(this.renderer.domElement);
    this.initControl(enableControl||false);

    // this.container.off('dbclick').dblclick(function () {//手工调整会引起的是camera的方向变化，而程序设置重力后调整的是helmet的方向变化.camera的方向变化主要是横向纵向和深度，二者不一致
    //     helmetModel.enableControl(!helmetModel.isControlEnabled());
    // });

    console.debug("3d头盔初始化完毕...");
    window.addEventListener('resize', function () {
        helmetModel.reSize();
    }, false);
    animate();//动画刷新
}

//重新计算容器尺寸
helmetModel.reCalSize = function () {
    var width = this.container.css("width");
    var height = this.container.css("height");
    this.containerWidth = width.substring(0, width.length - 2) * 1;//去掉结尾的px字符
    this.containerHeight = height.substring(0, height.length - 2) * 1;
    console.debug("头盔模型容器宽高=" + this.containerWidth + "," + this.containerHeight);
}

//设置相机大小
helmetModel.reSize = function () {
    this.reCalSize();
    this.camera.aspect = this.containerWidth / this.containerHeight;//aspect实际窗口的纵横比
    this.camera.updateProjectionMatrix();
    this.renderer.setSize(this.containerWidth, this.containerHeight);//调整大小
}

// helmetModel.loadObj = function () {
//     var that = this;
//     console.debug("3d头盔载入obj mtl模型...");
//     new THREE.MTLLoader()
//         .load( '/static/model/helmet_model.mtl', function ( materials ) {
//             materials.preload();
//             new THREE.OBJLoader()
//                 .setMaterials( materials )
//                 .load( '/static/model/helmet_model.obj', function ( object ) {
//                     // object.position.y = - 95;
//                     that.helmet = object;
//                     that.scene.add( that.helmet );
//                     console.debug("3d头盔载入obj mtl模型完毕=" + that.helmet);
//                 });
//         } );
// }

//载入stl模型文件
helmetModel.loadStl = function () {
    // load file
    console.debug("3d头盔载入stl模型...");
    var loader = new THREE.STLLoader();
    var that = this;
    loader.load('/static/model/helmet_model.stl', function (geometry) {
        var material = new THREE.MeshPhongMaterial({color: 0x808080, specular: 0x111111, shininess: 200});
        that.helmet = new THREE.Mesh(geometry, material);
        that.helmet.castShadow = true;
        that.helmet.receiveShadow = true;
        that.scene.add(that.helmet);
        // that.updateHelmetGravity(0, 0, 9.8);//设置默认方位角度
        console.debug("3d头盔载入stl模型完毕=" + that.helmet);
    });
}

//启动控件
helmetModel.initControl = function (enableDefault) {
    this.control = new THREE.OrbitControls(this.camera, this.renderer.domElement);
    // 如果使用animate方法时，将此函数删除
    //controls.addEventListener( 'change', render );
    // 使动画循环使用时阻尼或自转 意思是否有惯性
    this.control.enableDamping = true;
    //动态阻尼系数 就是鼠标拖拽旋转灵敏度
    //controls.dampingFactor = 0.25;
    //是否可以缩放
    this.control.enableZoom = true;
    //是否自动旋转
    this.control.autoRotate = true;
    //设置相机距离原点的最远距离
    this.control.minDistance = this.minDistance;
    //设置相机距离原点的最远距离
    this.control.maxDistance = this.maxDistance;
    //是否开启右键拖拽
    this.control.enablePan = true;
    this.control.update();

    this.enableControl(enableDefault);
}

//启动控件
helmetModel.enableControl = function (enable) {
    this.control.enabled = enable;
}

helmetModel.isControlEnabled = function () {
    return this.control.enabled;
}

//添加平行光
helmetModel.addDirectionalLight = function (x, y, z, color, intensity) {
    var directionalLight = new THREE.DirectionalLight(color, intensity);
    directionalLight.position.set(x, y, z);
    directionalLight.castShadow = true;
    directionalLight.shadowCameraNear = 2;
    directionalLight.shadowCameraFar = 200;
    directionalLight.shadowCameraLeft = -50;
    directionalLight.shadowCameraRight = 50;
    directionalLight.shadowCameraTop = 50;
    directionalLight.shadowCameraBottom = -50;
    directionalLight.distance = 0;
    directionalLight.shadowMapHeight = 1024;
    directionalLight.shadowMapWidth = 1024;
    this.scene.add(directionalLight);
}

//更新头盔的传感器数据
helmetModel.updateHelmetSensor = function (sensor) {
    if (sensor.xg != null && sensor.yg != null && sensor.zg != null) {
        var xg = (sensor.xg / 1900).toFixed(2);
        var yg = (sensor.yg / 1900).toFixed(2);
        var zg = (sensor.zg / 1900).toFixed(2);
        this.updateHelmetGravity(xg, yg, zg);
    }
}

//设置头盔3向的重力值
helmetModel.updateHelmetGravity = function (xg, yg, zg) {
    //经测试，头盔模型和传感器都是左右坐标系，但是头盔芯片安装的不太正
    var angels = this.getAngel(xg, yg, zg);
    console.debug("头盔重力加速度数据更新:"+xg+","+yg+","+zg+ "==>" + angels[0] + "," + angels[1] + "," + angels[2]);
    //在面对屏幕的右手坐标系中 Math.PI/8 表示旋转22.5度 Math.PI/4 表示旋转45度  Math.PI/2 表示旋转90度  Math.PI 表示旋转180度
    this.updateHelmetRotation(angels[0],angels[1],angels[2]);
}

helmetModel.updateHelmetRotation = function(x,y,z){
    if(this.helmet){
        this.helmet.rotation.x = x;//调整头盔绕x轴旋转。正对x轴指向看，则正数逆时针，负数顺时针旋转
        this.helmet.rotation.y = y;//调整头盔绕z轴旋转，正对z轴指向看，则正数顺时针
        this.helmet.rotation.z = z;//调整头盔绕y轴旋转，正对y轴指向看，则正数逆时针
    }
}

//根据重力加速度值计算偏角度
helmetModel.getAngel = function (x, y, z) {
    var angelX = Math.atan(x / Math.sqrt((y * y + z * z)));
    var angelY = Math.atan(y / Math.sqrt((x * x + z * z)));
    var angelZ = Math.atan(z / Math.sqrt((x * x + y * y)));
    // console.debug('根据重力加速度值计算角度:' + x + "," + y + "," + z + "==>" + angelX + "," + angelY + "," + angelZ);
    return [angelX.toFixed(3), angelY.toFixed(3), angelZ.toFixed(3)];
}

//重置
helmetModel.reset = function () {
    this.camera.position.set(0, -5000, 300);
    this.camera.scale.set(1, 1, 1);
    this.updateHelmetGravity(0, 0, 9.8);
}
