import os
import glob

replacements = {
    "import com.assabloy.livvi.streakdays.domain.model.": "import com.assabloy.livvi.streakdays.core.domain.model.",
    "import com.assabloy.livvi.streakdays.domain.usecase.": "import com.assabloy.livvi.streakdays.core.domain.usecase.",
    "import com.assabloy.livvi.streakdays.domain.GoalRepository": "import com.assabloy.livvi.streakdays.core.domain.repository.GoalRepository",
    "import com.assabloy.livvi.streakdays.ui.create.": "import com.assabloy.livvi.streakdays.feature.create.ui.",
    "import com.assabloy.livvi.streakdays.ui.dashboard.": "import com.assabloy.livvi.streakdays.feature.dashboard.ui.",
    "import com.assabloy.livvi.streakdays.ui.details.": "import com.assabloy.livvi.streakdays.feature.details.ui.",
    "import com.assabloy.livvi.streakdays.ui.navigation.": "import com.assabloy.livvi.streakdays.core.navigation.",
    "import com.assabloy.livvi.streakdays.ui.theme.": "import com.assabloy.livvi.streakdays.core.designsystem.",
    "import com.assabloy.livvi.streakdays.ui.widget.": "import com.assabloy.livvi.streakdays.core.widget.",
    "import com.assabloy.livvi.streakdays.data.local.": "import com.assabloy.livvi.streakdays.core.data.local.",
    "import com.assabloy.livvi.streakdays.data.repository.": "import com.assabloy.livvi.streakdays.core.data.repository."
}

for root, dirs, files in os.walk("app/src/main/java"):
    for file in files:
        if file.endswith(".kt"):
            path = os.path.join(root, file)
            with open(path, "r") as f:
                content = f.read()
            original = content
            for k, v in replacements.items():
                content = content.replace(k, v)
            
            # also replace package names in data
            if path.startswith("app/src/main/java/com/assabloy/livvi/streakdays/core/data"):
                content = content.replace("package com.assabloy.livvi.streakdays.data", "package com.assabloy.livvi.streakdays.core.data")
                
            if content != original:
                with open(path, "w") as f:
                    f.write(content)
